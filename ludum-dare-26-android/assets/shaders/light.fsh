#ifdef GL_ES
   #define LOWP lowp
   precision mediump float;
#else
   #define LOWP
#endif

varying vec2 vTextureCoord;

uniform sampler2D uTexture;

void main(void) {
	gl_FragColor = texture2D(uTexture, vTextureCoord);
}