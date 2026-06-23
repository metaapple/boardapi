package org.scoula.weather.controller;

import lombok.extern.log4j.Log4j2;
import org.scoula.weather.dto.WeatherDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RequestMapping("/weather")
@PropertySource("classpath://application.properties")
@Controller
@Log4j2
public class WeatherController {

    @Value("${weather.url}")
    private String URL;

    @Value("${weather.icon_url}")
    private String ICON_URL;

    @Value("${weather.api_key}")
    private String API_KEY;

    @GetMapping({"", "/{city}"})
    public String weather(@PathVariable(value = "city", required = false) String city,
                          Model model) {
        city = city == null ? "seoul" : city;

        RestTemplate restTemplate = new RestTemplate();
        String url = UriComponentsBuilder.fromHttpUrl(URL)
                .queryParam("q", city)
                .queryParam("units", "metric")
                .queryParam("APPID", API_KEY)
                .queryParam("lang", "kr")
                .toUriString();

        //get + url로 요청해서 dto에 넣어달라고 하자.
        WeatherDTO weather = restTemplate.getForObject(url, WeatherDTO.class);
        System.out.println("변환된 dto내용 " + weather);
        //jsp까지 전달할 데이터는 model의 속성으로 지정하면 됨.
        //브라우저가 요청할 때마다 서버는 그 요청을 응답할 수 있는
        //request, response 객체를 매번 만든다.
        //jsp를 실행시켜서 응답할 때까지 서버에서 가지고 있다가 사라짐.
        //컨트롤러가 forward하면서 jsp를 부르는데, 이때
        //톰킷이 생성한 request, response객체를 함께 전달함.
        //model속성으로 지정하면 request객체에 속성으로 붙어서 jsp까지 전달됨.
        String iconUrl = ICON_URL.formatted(weather.getWeather().get(0).getIcon());

        System.out.println(weather.getWeather().get(0).getIcon());
        //http://openweathermap.org/img/w/02d.png
        model.addAttribute("iconUrl", iconUrl);
        model.addAttribute("city", city);
        model.addAttribute("weather", weather);
        return "weather/today"; // /WEB-INF/views/weather/today.jsp
    }
}
