package com.designpatterns.creational;

public class FactoryPattern {

	public static void main(String ...strings){ 
		ObjectFactory.createShape(ShapeType.circle);
		ObjectFactory.createShape(ShapeType.rectangle);
		ObjectFactory.createShape(ShapeType.square);
	}
}

class ObjectFactory {
	
	public static Shape createShape(ShapeType shapeType){
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