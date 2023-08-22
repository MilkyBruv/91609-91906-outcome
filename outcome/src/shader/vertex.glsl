#version 400 core

layout(location = 0) in vec2 position;
layout(location = 1) in vec2 texCoord;

out vec2 fragTexCoord;

int width = 16 * 18;
int height = 9 * 18;

void main() {

    // vec2 newPosition = vec2(
        
    //     (position.x / width) * 2.0 - 1.0,
    //     (position.y / height) * 2.0 - 1.0
        
    // );

    gl_Position = vec4(position, 0.0, 1.0);
    fragTexCoord = texCoord;

}
