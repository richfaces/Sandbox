package org.richfaces.sandbox.captcha;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@SessionScoped
@ManagedBean
public class FormBean implements Serializable {

    private String name;

    private String submitedValue;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSubmitedValue()
    {
        return submitedValue;
    }

    public void submit()
    {
        submitedValue = name;
    }
}
