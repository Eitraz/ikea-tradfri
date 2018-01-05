package com.eitraz.ikea.tradfri.device;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Optional;

/**
 * https://github.com/IPSO-Alliance/pub/blob/master/docs/IPSO-Smart-Objects.pdf
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LightInfo {
    @JsonAlias("5850")
    private boolean on;

    // Range 0-255
    @JsonAlias("5851")
    private Integer dimmer;

    @JsonAlias("5706")
    private String color;

    @JsonAlias("5709")
    private Integer colorX;

    @JsonAlias("5710")
    private Integer colorY;

    @JsonAlias("5805")
    private Integer cumulativeActivePower;

    @JsonAlias("5820")
    private Integer powerFactor;

    @JsonAlias("5712")
    private Integer transitionTime;

    @JsonAlias("5852")
    private Integer onTime;

    public boolean isOn() {
        return on;
    }

    public Optional<Integer> getDimmer() {
        return Optional.ofNullable(dimmer);
    }

    public Optional<String> getColor() {
        return Optional.ofNullable(color);
    }

    public Optional<Integer> getColorX() {
        return Optional.ofNullable(colorX);
    }

    public Optional<Integer> getColorY() {
        return Optional.ofNullable(colorY);
    }

    public Optional<Integer> getCumulativeActivePower() {
        return Optional.ofNullable(cumulativeActivePower);
    }

    public Optional<Integer> getPowerFactor() {
        return Optional.ofNullable(powerFactor);
    }

    public Optional<Integer> getTransitionTime() {
        return Optional.ofNullable(transitionTime);
    }

    public Optional<Integer> getOnTime() {
        return Optional.ofNullable(onTime);
    }

    @Override
    public String toString() {
        return "LightInfo{" +
                "on=" + on +
                ", dimmer=" + dimmer +
                ", color='" + color + '\'' +
                ", colorX=" + colorX +
                ", colorY=" + colorY +
                ", cumulativeActivePower=" + cumulativeActivePower +
                ", powerFactor=" + powerFactor +
                ", transitionTime=" + transitionTime +
                ", onTime=" + onTime +
                '}';
    }
}
