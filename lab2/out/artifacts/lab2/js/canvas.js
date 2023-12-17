export default class Canvas{
    id;
    canvas;
    width;
    height;
    radius;
    static MAX_RADIUS = 5;

    constructor(id) {
        this.id = id;
        this.canvas = document.getElementById(id);
        this.radius = 0

        this.canvas.width = window.innerWidth / 3;
        this.canvas.height = this.canvas.width;
        this.width = this.canvas.width;
        this.height = this.canvas.height;
    }

    setRadius(radius) {
        this.radius = radius;
    }

    drawGraph() {
        const context = this.canvas.getContext("2d");
        let width = this.canvas.width;
        let height = this.canvas.height;

        const arrow_height = 0.05 * width;
        const per = (this.radius / Canvas.MAX_RADIUS) * 0.4;
        context.fillStyle ="#989898"
        this.drawTriangle(context, {x: (0.5 - per / 2) * width,  y: height / 2}, {x: width / 2,  y: height / 2}, {x: width / 2,  y: (0.5 - per / 2) * height}, true);
        context.fillRect(height / 2, (0.1 + (0.4 - per) + per / 2) * width, per * height, (per / 2) * width);

        context.beginPath();
        context.moveTo(width / 2, height / 2);
        context.lineTo((0.5 + per) * width, height / 2);
        context.arc(width / 2, height / 2, per * width, 0, Math.PI / 2);
        context.lineTo(width / 2, (0.5 + per) * height);
        context.fill();


        context.strokeStyle = "#000000"
        context.lineWidth = 2;

        this.drawLine(context, 0, height / 2, width, height / 2);
        this.drawLine(context, width / 2, 0, width / 2, height);
        this.drawLine(context, width / 2, 0, width / 2 - arrow_height / 2, arrow_height);
        this.drawLine(context, width / 2, 0, width / 2 + arrow_height / 2, arrow_height);
        this.drawLine(context, width, height / 2, width - arrow_height, height / 2 - arrow_height / 2);
        this.drawLine(context, width, height / 2, width - arrow_height, height / 2 + arrow_height / 2);

        for (let i = 0; i <= Canvas.MAX_RADIUS * 2; i ++) {
            if (i === Canvas.MAX_RADIUS) continue;
            this.drawLine(context, width / 2 - 0.01 * width, (0.1 + i * 0.8 / (Canvas.MAX_RADIUS * 2)) * height,
                width / 2 + 0.01 * width, (0.1 + i * 0.8 / (Canvas.MAX_RADIUS * 2)) * height);
        }
        for (let i = 0; i <= Canvas.MAX_RADIUS * 2; i ++) {
            if (i === Canvas.MAX_RADIUS) continue;
            this.drawLine(context, (0.1 + i * 0.8 / (Canvas.MAX_RADIUS * 2)) * width, height / 2 - 0.01 * height,
                (0.1 + i * 0.8 / (Canvas.MAX_RADIUS * 2)) * width, height / 2 + 0.01 * height);
        }

        // context.font = "15px Arial";
        // context.fillText(Canvas.MAX_RADIUS.toString(), width / 2 + 8, (0.1) * height + 6);
        // context.fillText(Canvas.MAX_RADIUS.toString(), width - (0.1) * width - 6, height / 2 - 10);

    }

    clear() {
        const context = this.canvas.getContext('2d');
        context.clearRect(0, 0, this.width * 2, this.height * 2);
    }

    drawLine(ctx, from_x, from_y, tox, toy) {
        ctx.beginPath();
        ctx.moveTo(from_x, from_y);
        ctx.lineTo(tox, toy);
        ctx.stroke();
    }

    drawTriangle(ctx, point1, point2, point3, filled) {
        ctx.beginPath();
        ctx.moveTo(point1.x, point1.y);
        ctx.lineTo(point2.x, point2.y);
        ctx.lineTo(point3.x, point3.y);
        ctx.closePath();
        filled ? ctx.fill() : ctx.stroke();
    }

    drawResult(response) {
        let [dumb_x, dumb_y] = this.normal_to_dumb((response.x / response.r) * this.radius, (response.y / response.r) * this.radius);

        let ctx = this.canvas.getContext("2d");
        // Пример: рисуем красную точку

        ctx.fillStyle = response.hit ? "green" : "red";
        ctx.beginPath();
        ctx.arc(dumb_x, dumb_y, (1/100) * this.width, 0, Math.PI * 2);
        ctx.fill();
    }

    dumb_to_normal(dumb_x, dumb_y) {
        let normal_x, normal_y;
        dumb_x -= this.width * 0.1;
        dumb_y -= this.height * 0.1;

        let l_width = this.width * 0.8 / (Canvas.MAX_RADIUS * 2);
        let l_height = this.height * 0.8 / (Canvas.MAX_RADIUS * 2);
        dumb_x = dumb_x / l_width;
        dumb_y = dumb_y / l_height;
        normal_x = dumb_x - Canvas.MAX_RADIUS;
        normal_y = Canvas.MAX_RADIUS - dumb_y;
        return [normal_x, normal_y];
    }

    normal_to_dumb(normal_x, normal_y) {
        let dumb_x, dumb_y;
        dumb_x =  normal_x + Canvas.MAX_RADIUS;
        dumb_y = Canvas.MAX_RADIUS - normal_y;
        let l_width = this.width * 0.8 / (Canvas.MAX_RADIUS * 2);
        let l_height = this.height * 0.8 / (Canvas.MAX_RADIUS * 2);
        dumb_x = dumb_x * l_width;
        dumb_y = dumb_y * l_height;
        dumb_x += this.width * 0.1;
        dumb_y += this.height * 0.1;
        return [dumb_x, dumb_y];
    }
    drawSavedPoints(clickedPoints) {
        clickedPoints.forEach((result) => this.drawResult(result));
    }
}