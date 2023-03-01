package edu.eci.ieti.repository;

public class ComponentRequest<T> {

    private T component;

    public ComponentRequest() {
    }

    public ComponentRequest(T component) {
        this.component = component;
    }

    public T getComponent() {
        return component;
    }

    public void setComponent(T component) {
        this.component = component;
    }

}
