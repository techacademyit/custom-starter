package techacademy.example.customstarter;

import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Configuration;

@Configuration
@Import({ WebfluxClient.class })
public class CustomAutoConfiguration
{
}