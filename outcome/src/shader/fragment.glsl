#version 400 core

in vec2 fragTexCoord;
uniform sampler2D textureSampler; // Texture unit

out vec4 fragColor;

vec4 fixColourInversion(vec4 color);

void main() {

    vec4 col = texture(textureSampler, fragTexCoord);

    vec4 newCol = vec4(col.r, 0.0, col.b, col.a);

    fragColor = fixColourInversion(newCol);

}

vec4 fixColourInversion(vec4 color) {

    return vec4(color.b, color.g, color.r, color.a);

}
