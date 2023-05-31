package dgrubjesic.omni.users;

import io.rsocket.core.RSocketServer;
import io.rsocket.frame.decoder.PayloadDecoder;
import io.rsocket.transport.netty.server.TcpServerTransport;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.blockhound.BlockHound;

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		BlockHound.install();
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
