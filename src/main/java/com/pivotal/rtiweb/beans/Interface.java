package com.pivotal.rtiweb.beans;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class Interface implements Serializable
{
    private String interfaceName;
    private String count;

    public Interface()
    {
    }

    public Interface(String interfaceName, String count) {
        this.interfaceName = interfaceName;
        this.count = count;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Interface{" +
                "interfaceName='" + interfaceName + '\'' +
                ", count='" + count + '\'' +
                '}';
    }
}
