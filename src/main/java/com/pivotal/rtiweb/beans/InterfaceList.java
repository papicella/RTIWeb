package com.pivotal.rtiweb.beans;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="interfaces")
@SuppressWarnings("serial")
public class InterfaceList
{
    private List<Interface> interfaces;

    protected InterfaceList() {}   // Keep JAXB happy

    public InterfaceList(List<Interface> interfaces)
    {
        this.interfaces = interfaces;
    }

    @XmlElement(name="interface")
    public List<Interface> getInterfaces()
    {
        return interfaces;
    }

    public void setInterfaces(List<Interface> interfaces)
    {
        this.interfaces = interfaces;
    }
}
