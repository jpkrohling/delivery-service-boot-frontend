package eu.javaland.tracing.frontend;

import com.uber.jaeger.Configuration;
import com.uber.jaeger.samplers.ConstSampler;
import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableZuulProxy
public class FrontendApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrontendApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.rootUri("http://localhost:8080/").build();
    }

    @Bean
    public Tracer produceTracer() {
        return new Configuration("javaland-boot-frontend")
                .withReporter(
                        new Configuration.ReporterConfiguration()
                                .withLogSpans(true)
                )
                .withSampler(
                        new Configuration.SamplerConfiguration()
                                .withType(ConstSampler.TYPE)
                                .withParam(1)
                )
                .getTracerBuilder()
                .build();
    }
}
