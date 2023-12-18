package example;

import example.db.DAOFactory;
import example.db.ResultDAO;
import example.entity.ResultEntity;
import example.utils.AreaChecker;
import example.value.YValueBean;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Managed bean for handling results in JSF application.
 * This bean is responsible for managing operations related to result entities.
 */
@Data
@Slf4j
@ApplicationScoped
public class ResultsControllerBean implements Serializable {
    private YValueBean yValueBean;

    private ResultDAO resultDAO;
    private ArrayList<ResultEntity> results = new ArrayList<>();

    @PostConstruct
    public void init() {
        resultDAO = DAOFactory.getInstance().getResultDAO();
        var resultsEntities = resultDAO.getAllResults();
        results = new ArrayList<>(resultsEntities);
        Collections.reverse(results);
        log.info("Results initialized with {} entries.", results.size());
    }

    public void addResult(Double x, Double y, Double r) {
        LocalDateTime time = LocalDateTime.now();
        boolean success = AreaChecker.isInArea(x, y, r);
        FacesContext fCtx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false);
        String sessionId = session.getId();
        long duration = Duration.between(time, LocalDateTime.now()).toMillis();
        ResultEntity entity = ResultEntity.builder().x(x).y(y).r(r).success(success).watch(ZonedDateTime.now()).duration(duration).sessionId(sessionId).build();
        results.add(0, entity);
        // add to db
        DAOFactory.getInstance().getResultDAO().addNewResult(entity);
        String script1 = String.format(Locale.US, "window.canvas.drawResult({x: %f, y: %f, r: %f, success: %b});", x, y, r, success);
        FacesContext.getCurrentInstance().getPartialViewContext().getEvalScripts().add(script1);
        String script2 = String.format(Locale.US, "window.addPoint(%f, %f, %f, %b);", x, y, r, success);
        FacesContext.getCurrentInstance().getPartialViewContext().getEvalScripts().add(script2);

    }

    public List<ResultEntity> getSessionResults(){
        FacesContext fCtx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false);
        String sessionId = session.getId();
        return results.stream()
                .filter(result -> result.getSessionId().equals(sessionId))
                .collect(Collectors.toList());
    }
    public void clearResults() {
        DAOFactory.getInstance().getResultDAO().clearResults();

        results.clear();
        log.info("All results cleared from database and local array.");
    }
}

