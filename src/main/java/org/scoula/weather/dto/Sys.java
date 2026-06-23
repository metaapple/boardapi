package org.scoula.weather.dto;

import lombok.Data;

@Data
public class Sys{
	private String country;
	private int sunrise;
	private int sunset;
}