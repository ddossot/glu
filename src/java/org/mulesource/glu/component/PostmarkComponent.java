/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mulesource.glu.component;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONStringer;
import org.mulesource.glu.component.api.ComponentInfo;
import org.mulesource.glu.component.api.HTTP;
import org.mulesource.glu.component.api.HttpMethod;
import org.mulesource.glu.component.api.HttpResult;
import org.mulesource.glu.component.api.Parameter;
import org.mulesource.glu.component.api.Result;
import org.mulesource.glu.component.api.Service;

/*
 * See http://developer.postmarkapp.com/#build-your-own 
 */
@ComponentInfo(name = "PostmarkComponent", description = "A component that interacts with PostMark", doc = "See http://postmarkapp.com for more information.")
public class PostmarkComponent extends BaseComponent {
    private static final String POSTMARK_API_BASE_URI = "http://api.postmarkapp.com/email";

    private enum BodyType {
        HTML("HtmlBody"), TEXT("TextBody");
        private final String parameterName;

        BodyType(String parameterName) {
            this.parameterName = parameterName;
        }
    };

    @Parameter(name = "serverToken", desc = "Postmark Server Token")
    public String serverToken = null;

    @Parameter(name = "senderEmail", desc = "Sender e-mail")
    public String senderEmail = null;
    @Service(description = "POST a new HTML email to this resource, providing the addressee and subject as parameters.")
    public Result htmlEmail(final HttpMethod method, String body,
            @Parameter(name = "to", desc = "Destination address for the e-mail") final String to,
            @Parameter(name = "subject", desc = "Subject of the e-mail") final String subject) throws JSONException {

        return sendEmail(method, BodyType.HTML, body, to, subject);
    }

    @Service(description = "POST a new plain text email to this resource, providing the addressee and subject as parameters.")
    public Result textEmail(final HttpMethod method, String body,
            @Parameter(name = "to", desc = "Destination address for the e-mail") final String to,
            @Parameter(name = "subject", desc = "Subject of the e-mail") final String subject) throws JSONException {

        return sendEmail(method, BodyType.TEXT, body, to, subject);
    }

    protected Result sendEmail(final HttpMethod method, BodyType bodyType, String body, final String to, final String subject)
            throws JSONException {
        if (method == HTTP.POST) {
            final String requestData = new JSONStringer().object().key("From").value(senderEmail).key("To").value(to).key(
                    "Subject").value(subject).key(bodyType.parameterName).value(body).endObject().toString();

            Map<String, String> headers = new HashMap<String, String>();
            headers.put("Accept", "application/json");
            headers.put("Content-Type", "application/json");
            headers.put("X-Postmark-Server-Token", serverToken);

            final HttpResult res = httpPost(POSTMARK_API_BASE_URI, requestData, headers);
            return Result.ok(res.data);
        }

        return Result.badRequest("Unsupported method: " + method);
    }

}
