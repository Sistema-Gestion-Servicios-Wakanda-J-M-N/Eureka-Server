package org.example.eurekaserver;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.Invoker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import java.io.File;
import java.util.Collections;
import java.util.Properties;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication.class, args);
		runAllTasks();
	}

	private static void runAllTasks() {
		runMavenCleanInstall();
		if (!isDockerNetworkExists("backend-network")) {
			createDockerNetwork("backend-network");
		}
		if (!isDockerImageExists("eureka-server")) {
			runDockerComposeBuild();
		}
		if (!isDockerContainerRunning("eureka-server")) {
			runDockerComposeUp();
		}
	}

	private static boolean isDockerNetworkExists(String networkName) {
		ProcessBuilder processBuilder = new ProcessBuilder();
		processBuilder.command("docker", "network", "ls", "-q", "--filter", "name=" + networkName);
		try {
			Process process = processBuilder.start();
			int exitCode = process.waitFor();
			return exitCode == 0 && process.getInputStream().read() != -1;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private static void createDockerNetwork(String networkName) {
		ProcessBuilder processBuilder = new ProcessBuilder();
		processBuilder.command("docker", "network", "create", networkName);
		processBuilder.inheritIO();
		try {
			Process process = processBuilder.start();
			int exitCode = process.waitFor();
			if (exitCode != 0) {
				System.err.println("Error: docker network create failed with exit code " + exitCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void runMavenCleanInstall() {
		InvocationRequest request = new DefaultInvocationRequest();
		request.setPomFile(new File("pom.xml"));
		request.setGoals(Collections.singletonList("clean install"));

		Invoker invoker = new DefaultInvoker();
		invoker.setMavenExecutable(new File("mvnw")); // Use the Maven Wrapper script

		try {
			invoker.execute(request);
			System.out.println("Maven build completed successfully!");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error during Maven build.");
		}
	}

	private static void runDockerComposeBuild() {
		ProcessBuilder processBuilder = new ProcessBuilder();
		processBuilder.command("docker-compose", "-f", "C:/Users/lopee/Documents/GitHub/Eureka-Server/docker-compose.yml", "build");
		processBuilder.inheritIO();

		try {
			Process process = processBuilder.start();
			int exitCode = process.waitFor();
			if (exitCode != 0) {
				System.err.println("Error: docker-compose build failed with exit code " + exitCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void runDockerComposeUp() {
		ProcessBuilder processBuilder = new ProcessBuilder();
		processBuilder.command("docker-compose", "-f", "/Users/lopee/Documents/GitHub/Eureka-Server/docker-compose.yml", "up", "-d");
		processBuilder.inheritIO();

		try {
			Process process = processBuilder.start();
			int exitCode = process.waitFor();
			if (exitCode != 0) {
				System.err.println("Error: docker-compose up failed with exit code " + exitCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static boolean isDockerImageExists(String imageName) {
		ProcessBuilder processBuilder = new ProcessBuilder();
		processBuilder.command("docker", "images", "-q", imageName);
		try {
			Process process = processBuilder.start();
			int exitCode = process.waitFor();
			return exitCode == 0 && process.getInputStream().read() != -1;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private static boolean isDockerContainerRunning(String containerName) {
		ProcessBuilder processBuilder = new ProcessBuilder();
		processBuilder.command("docker", "ps", "-q", "--filter", "name=" + containerName);
		try {
			Process process = processBuilder.start();
			int exitCode = process.waitFor();
			return exitCode == 0 && process.getInputStream().read() != -1;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}

