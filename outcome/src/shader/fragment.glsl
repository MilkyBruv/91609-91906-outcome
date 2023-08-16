#version 400 core

in vec2 fragTexCoord;
uniform sampler2D textureSampler; // Texture unit

out vec4 fragColor;

void main() {

    vec4 col = texture(textureSampler, fragTexCoord);

    fragColor = vec4(col.r, 0.0, 0.0, col.a);

}
