package com.example.poc.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.bpmsremote.client2.BpmsClient;
import com.example.bpmsremote.client2.BpmsClientUtil;
import com.example.bpmsremote.model.ProcessInstanceLog;
import com.example.poc.model.ProcessInstance;
import com.google.gson.Gson;


@WebServlet("/instances")
public class ListProcessServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(ListProcessServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        BpmsClient bpmsClient = BpmsClientUtil.setUp();
        List<ProcessInstance> instances = new ArrayList<ProcessInstance>();
        try {
            List<ProcessInstanceLog> logs = bpmsClient.listInstances();
            for (ProcessInstanceLog log : logs){
                if (log.getLog() != null && log.getLog().getStatus() != 3){
                    ProcessInstance p1 = new ProcessInstance();
                    p1.setProcessInstanceId(log.getLog().getProcessInstanceId());
                    p1.setProcessName(log.getLog().getExternalId());
                    Date UTCDate = new Date(log.getLog().getStart());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    String result = simpleDateFormat.format(UTCDate);
                    p1.setStart(result);
                    if (log.getLog().getEnd() != 0 && log.getLog().getEnd() != -1){
                        UTCDate = new Date(log.getLog().getEnd());
                        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                        result = simpleDateFormat.format(UTCDate);
                        p1.setEnd(result);
                    } else {
                        p1.setEnd("");
                    }
                    instances.add(p1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.getWriter().write(new Gson().toJson(instances));
    }
}
