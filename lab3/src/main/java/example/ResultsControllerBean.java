package example;

import example.db.DAOFactory;
import example.db.ResultDAO;
import example.entity.ResultEntity;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Managed bean for handling results in JSF application.
 * This bean is responsible for managing operations related to result entities.
 */
@Data
@Slf4j
public class ResultsControllerBean implements Serializable {
    private XBean xBean;

    private ResultDAO resultDAO;
    private ArrayList<ResultEntity> results = new ArrayList<>();

    @PostConstruct
    public void init() {
        resultDAO = DAOFactory.getInstance().getResultDAO();
        var resultsEntities = resultDAO.getAllResults();
        results = new ArrayList<>(resultsEntities);
        log.info("Results initialized with {} entries.", results.size());
    }

    public void addResult(Double x) {
        ResultEntity entity = ResultEntity.builder().x(x).build();
        results.add(entity);
        // add to db
        DAOFactory.getInstance().getResultDAO().addNewResult(entity);
        log.info("Added new result to the db: X={}", x);
    }
}

