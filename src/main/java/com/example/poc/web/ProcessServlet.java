package com.example.poc.web;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.bpmsremote.client2.BpmsClient;
import com.example.bpmsremote.client2.BpmsClientUtil;
import com.example.bpmsremote.model.StartProcessResponse;
import com.google.gson.Gson;


@WebServlet("/process")
public class ProcessServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String deploymentId = request.getParameter("deploymentId");
        String processDefId = request.getParameter("processDefId");
        
        LOGGER.info("Process Starting {} - {}", deploymentId, processDefId);
        
        Map<String, String> params = new HashMap<String, String>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramKey = parameterNames.nextElement();
            if (!paramKey.equals("deploymentId") && !paramKey.equals("processDefId")) {
                String paramValue = request.getParameter(paramKey);
                LOGGER.info("Param-{}:{}", paramKey, paramValue);
                params.put(paramKey, paramValue);
            }
        }
        
        BpmsClient bpmsClient = BpmsClientUtil.getBpmsClient();
        try {
            StartProcessResponse resp = bpmsClient.startProcess(deploymentId, processDefId, params);
            response.getWriter().write(new Gson().toJson(resp));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
