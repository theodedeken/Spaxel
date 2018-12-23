package code.graphics;

import code.util.BufferUtils;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

/**
 * Created by theod on 16-7-2017.
 */
public class Quad {
    protected int vao;

    private int vbo;
    private int ibo;
    private int tbo;
    public float[] vertices = new float[]{
            -.5f,-.5f,0,
            -.5f,.5f,0,
            .5f,.5f,0,
            .5f,-.5f,0
    };

    byte[] indices = new byte[] {
            0, 1, 3,
            3, 1, 2
    };

    float[] texCoords = new float[] {
            0, 1,
            0, 0,
            1, 0,
            1, 1
    };

    public Quad(){
        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(vertices), GL_STATIC_DRAW);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(0);

        tbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, tbo);
        glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(texCoords), GL_STATIC_DRAW);
        glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(1);

        ibo = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, BufferUtils.createByteBuffer(indices), GL_STATIC_DRAW);
    }

    public void bind(){
        glBindVertexArray(vao);
    }

    public int getVao() {
        return vao;
    }

    public int getVbo() {
        return vbo;
    }

    public int getIbo() {
        return ibo;
    }

    public int getTbo() {
        return tbo;
    }
}
