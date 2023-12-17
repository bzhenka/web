package beans;

import beans.Result;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResultsList implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final List<Result> results;

    public ResultsList() {
        this.results = new ArrayList<>();
    }

    public void addResult(Result result) {
        this.results.add(result);
    }

    public List<Result> getResults() {
        return results;
    }
}
