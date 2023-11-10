<?php
class dataChecking
{
    private $x;
    private $y;
    private $r;

    public function __construct($x, $y, $r)
    {
        $this->x = $x;
        $this->y = $y;
        $this->r = $r;
    }

    public function checkData()
    {
        return $this->checkX() && $this->checkY() && $this->checkR();
    }

    private function checkX()
    {
        return in_array($this->x, array(-3, -2, -1, 0, 1, 2, 3, 4, 5));
    }

    private function checkY()
    {
        return is_numeric($this->y) && ($this->y >= -3 && $this->y <= 3);
    }

    private function checkR()
    {
        return in_array($this->r, array(2, 2.5, 3, 3.5, 4, 4.5, 5));
    }
}
class AreaChecker {
    public static function isInArea($x, $y, $r) {
        if ($x <= 0 && $y >= 0) { 
            return ($x * $x + $y * $y) <= ($r * $r);
        }
        if ($x <= 0 && $y <= 0) { 
            return ($x >= -$r / 2) && ($y >= -$r);
        }
        if ($x >=  0 && $y >= 0) { 
            return ($x <= $r) && ($y <= $r / 2) && (2 * $y + $x <= $r);
        }
        return false; 
    }
}



if (isset($_GET["tz"])) {
    try {
        new DateTimeZone($_GET["tz"]);
        date_default_timezone_set($_GET["tz"]);
    } catch(Exception $e){
    }
}


$start_time = microtime(true);

if ($_SERVER["REQUEST_METHOD"] !== "GET") {
    http_response_code(405);
    echo 'Incorrect, try again Only GET method accepted.';
    return;
}

$x = $_GET['coordinateX'];
$y = $_GET['coordinateY'];
$r = $_GET['coordinateR'];

$validator = new dataChecking($x, $y, $r);
if ($validator->checkData()) {
    $isInArea = AreaChecker::isInArea($x, $y, $r);
    
    $end_time = microtime(true);
    $execution_time = ($end_time - $start_time) * 1000;
    $current_time = date('Y-m-d H:i:s');

    $newResult = array(
        "x" => $x,
        "y" => $y,
        "r" => $r,
        "coordsStatus" => $isInArea,
        "currentTime" => $current_time,
        "benchmarkTime" => $execution_time
    );


    header('Content-Type: application/json');
    echo json_encode($newResult);
} else {
    http_response_code(422);
    echo "Некоректные данные.";
}
?>