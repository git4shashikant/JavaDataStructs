package com.designpatterns.creational;

public class AbstractFactoryPattern {

	public static void main (String ... strings) {
		
		ColorFactory factory  = AbstractFactory.getColorFactory();
		factory.createColor(ColorType.black);
		
	}
}


abstract class AbstractFactory {
	
	public static ColorFactory getColorFactory(){
		return new ColorFactory();	
	}
	
	public static ShapeFactory getShapeFactory(){
		return new ShapeFactory();	
	}
}

class ColorFactory {
	public Color createColor(ColorType colorType){
		Color color = null;
		if (colorType.equals(ColorType.black)){
			color = new Black();
		} else if (colorType.equals(ColorType.blue)){
			color = new Blue();
		} else if (colorType.equals(ColorType.green)) {
			color = new Green();
		}
		return color;	
	}
}

enum ColorType {
	blue,
	green,
    black
}

interface Color{}
class Black implements Color{}
class Green implements Color{}
class Blue implements Color{}

class ShapeFactory {
	
	public Shape createShape(ShapeType shapeType){
		Shape shape = null;
		if (shapeType.equals(ShapeType.circle)){
			shape = new Circle();
		} else if (shapeType.equals(ShapeType.rectangle)){
			shape = new Rectangle();
		} else if (shapeType.equals(ShapeType.square)) {
			shape = new Square();
		}
		return shape;	
	}
}

enum ShapeType {
	circle,
	rectangle,
	square;
}

interface Shape {}
class Circle implements Shape {}
class Rectangle implements Shape {}
class Square implements Shape {}