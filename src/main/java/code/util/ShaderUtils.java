package code.util;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;

public final class ShaderUtils {

	private static FileToStringLoader loader = new FileToStringLoader();

	private ShaderUtils() {
	}

	public static int load(String vertPath, String fragPath) {
		String vert = loader.loadAsString(vertPath);
		String frag = loader.loadAsString(fragPath);
		return create(vert, frag);
	}

	public static int create(String vert, String frag) {
		int vertID = glCreateShader(GL_VERTEX_SHADER);
		int fragID = glCreateShader(GL_FRAGMENT_SHADER);
		glShaderSource(vertID, vert);
		glShaderSource(fragID, frag);

		glCompileShader(vertID);
		if (glGetShaderi(vertID, GL_COMPILE_STATUS) == GL_FALSE) {
			System.err.println("Failed to compile vertex shader!");
			System.err.println(glGetShaderInfoLog(vertID));
			return -1;
		}

		glCompileShader(fragID);
		if (glGetShaderi(fragID, GL_COMPILE_STATUS) == GL_FALSE) {
			System.err.println("Failed to compile fragment shader!");
			System.err.println(glGetShaderInfoLog(fragID));
			return -1;
		}

		int program = glCreateProgram();
		glAttachShader(program, vertID);
		glAttachShader(program, fragID);
		glLinkProgram(program);
		glValidateProgram(program);

		glDeleteShader(vertID);
		glDeleteShader(fragID);

		return program;
	}

}
