package com.project.client.dispatcher;

import com.project.client.domain.Request;
import com.project.client.domain.Response;

public interface IParseMessageCommand {

    public Response parseMessage(Request request);
}
