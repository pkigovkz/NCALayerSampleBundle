package kz.gov.pki.samplebundle;

import org.json.JSONObject;
import org.json.JSONStringer;
import kz.gov.pki.osgi.layer.api.ModuleService;

/**
 * @author aslan
 * It is just an example. You can implement everything in your own way!
 * Please, check Import-Package in osgi.properties. If you need specific
 * packages add ones. Remove unused packages.
 * If you'd like to expose your packages to other bundles,
 * use Export-Package property.
 */
public class ModuleServiceImpl implements ModuleService {

    @Override
    public String process(String jsonString, String jsonAddInfo) {
        // in order to log to ncalayer.log
        // import static kz.gov.pki.samplebundle.common.BundleLog.LOG;
        // LOG.info("something");
        String ret;
        JSONStringer stringer = new JSONStringer();
        JSONObject jsonObj = new JSONObject(jsonString);
        if (jsonAddInfo != null) {
            JSONObject jsonInfoObj = new JSONObject(jsonAddInfo);
            String origin = jsonInfoObj.optString("origin");
            // You can refuse a connection, if an origin is not your site
            // e.g. https://nca.pki.gov.kz
            //
        }
        String jsonMethod = jsonObj.optString("method");
        if (jsonMethod.equals("salemAit")) {
            JSONObject jsonArgs = jsonObj.getJSONObject("args");
            String username = jsonArgs.optString("username", "Silent Bob");
            ret = new JSONStringer().object().key("success").value(true).
                    key("body").object().key("result").value("Salem, " + username)
                    .endObject().endObject()
                    .toString();
        } else {
            ret = stringer.object().key("success").value(false).
                    key("errorCode").value("method_not_defined").
                    key("message").value("Art is a BANG!").endObject().toString();
        }

        return ret;
    }

}
