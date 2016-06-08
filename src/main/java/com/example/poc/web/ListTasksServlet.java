package com.example.poc.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.bpmsremote.client2.BpmsClient;
import com.example.bpmsremote.client2.BpmsClientUtil;
import com.example.bpmsremote.model.JaxbTaskSummary;
import com.example.bpmsremote.model.TaskSummary;
import com.example.poc.model.Task;
import com.google.gson.Gson;


@WebServlet("/tasks")
public class ListTasksServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(ListTasksServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        BpmsClient bpmsClient = BpmsClientUtil.getBpmsClient();
        List<Task> tasks = new ArrayList<Task>();
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("potentialOwner", "wguo");
            TaskSummary task = bpmsClient.listAssignTask(params);
            for (JaxbTaskSummary jaxbTask : task.getTaskSummaryList()) {
                Task t1 = new Task();
                t1.setId(jaxbTask.getId());
                t1.setTaskName(jaxbTask.getName());
                t1.setProcessName(jaxbTask.getDeploymentId());
                t1.setStatus(jaxbTask.getStatus());
                Date UTCDate = new Date(jaxbTask.getCreatedOn());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String result = simpleDateFormat.format(UTCDate);
                t1.setCreatedOn(result);
                tasks.add(t1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.getWriter().write(new Gson().toJson(tasks));

    }
}
