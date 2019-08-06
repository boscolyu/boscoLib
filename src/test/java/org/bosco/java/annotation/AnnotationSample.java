package org.bosco.java.annotation;

import java.lang.reflect.Method;

@UserAnnotation(number=0)
public class AnnotationSample {

	 @UserAnnotation(number=1)
	    public static void main(String[] args) {
		 AnnotationSample sample = new AnnotationSample();
	        sample.checkAnnotations(AnnotationSample.class);
	    }
	 
	    @UserAnnotation(number=2)
	    public void annotationSample1() {
	    }
	    @UserAnnotation(number=3, text="second")
	    public void annotationSample2() {
	    }
	    @UserAnnotation(number=4, text="third")
	    public void annotationSample3() {
	    }
	 
	    public void checkAnnotations(Class useClass){
	        Method[] methods = useClass.getDeclaredMethods();
	        for ( Method tempMethod:methods ){
	            UserAnnotation annotation = tempMethod.getAnnotation(UserAnnotation.class);
	            if ( annotation != null ){
	                int number = annotation.number();
	                String text = annotation.text();
	                System.out.println(tempMethod.getName() + "() : number=" + number + " text=" + text);
	            } else {
	                System.out.println(tempMethod.getName() + "() : annotation is null");
	            }
	        }
	    }
	    
}
