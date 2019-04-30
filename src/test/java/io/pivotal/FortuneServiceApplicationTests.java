package io.pivotal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.jdbc.core.JdbcTemplate;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FortuneServiceApplication.class)
public class FortuneServiceApplicationTests {

    @Test
    public void contextLoads() throws Exception {

    }

    @Autowired
    private JdbcTemplate template;

    @Test
    public void testDefaultSettings() throws Exception {
        assertThat(this.template.queryForObject("SELECT COUNT(*) FROM fortune WHERE text='Freedom and responsibility is rooted in back compatibility.'",
                Integer.class)).isEqualTo(1);
    }

}
