#ifdef GL_ES
   #define LOWP lowp
   precision mediump float;
#else
   #define LOWP
#endif

uniform sampler2D uTexture1;
uniform sampler2D uTexture2;

uniform float uFactor1;
uniform float uFactor2;

varying vec2 vTextureCoord;

//float rand(vec2 co) {
//	return fract(sin(dot(co.xy ,vec2(12.9898,78.233))) * 43758.5453 * uTime);
//}

void main(void) {

	vec4 color1 = texture2D(uTexture1, vTextureCoord) * uFactor1;
	vec4 color2 = texture2D(uTexture2, vTextureCoord) * uFactor2;
	
	vec4 result = color1 + color2;
	
	
	gl_FragColor = result;
}