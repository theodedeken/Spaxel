package code.graphics.shaders;

import code.math.VectorD;

import static org.lwjgl.opengl.GL20.*;

/**
 * Created by theod on 25-7-2017.
 */
public class BlurShaderProgram extends ShaderProgram {
    private int texSamplerLocation;
    private int radiusLocation;
    private int resolutionLocation;
    private int sizeLocation;
    private int dirLocation;

    public BlurShaderProgram(String vertexShader, String fragmentShader) {
        super(vertexShader, fragmentShader);
        texSamplerLocation = glGetUniformLocation(program, "tex_sampler");
        radiusLocation = glGetUniformLocation(program, "radius");
        resolutionLocation = glGetUniformLocation(program, "resolution");
        sizeLocation = glGetUniformLocation(program, "size");
        dirLocation = glGetUniformLocation(program, "dir");
    }

    public void setTexSampler(int sampler) {
        glUniform1i(texSamplerLocation, sampler);
    }

    public void setRadius(double radius) {
        glUniform1f(radiusLocation, (float) radius);
    }

    public void setResolution(double resolution) {
        glUniform1f(resolutionLocation, (float) resolution);
    }

    public void setSize(int size) {
        glUniform1i(sizeLocation, size);
    }

    public void setDir(VectorD dir) {
        glUniform2f(dirLocation, (float) dir.getValue(0), (float) dir.getValue(1));
    }
}
