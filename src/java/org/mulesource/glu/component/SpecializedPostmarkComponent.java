package org.mulesource.glu.component;

import org.json.JSONException;
import org.mulesource.glu.component.api.ComponentInfo;
import org.mulesource.glu.component.api.HttpMethod;
import org.mulesource.glu.component.api.Parameter;
import org.mulesource.glu.component.api.Result;
import org.mulesource.glu.component.api.Service;

/*
 * See http://developer.postmarkapp.com/#build-your-own 
 */
@ComponentInfo(name = "SpecializedPostmarkComponent", description = "A component that interacts with PostMark and exposes specialized methods", doc = "See http://postmarkapp.com for more information.")
public class SpecializedPostmarkComponent extends PostmarkComponent {

    @Parameter(name = "adminEmail", desc = "Admin e-mail")
    public String adminEmail = null;

    @Service(description = "POST a new plain text email to this resource.")
    public Result adminAlert(final HttpMethod method, String body) throws JSONException {
        return textEmail(method, body, adminEmail, "Admin Alert!");
    }
}
