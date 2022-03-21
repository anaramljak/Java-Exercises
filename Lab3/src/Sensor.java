package vjezba3;

public class Sensor {
	private String name;
	private String dataType;
	private int factor;
	private double min;
	private double max;
	private double result;
	private String unit;


	public Sensor(String name, String dataType, int factor, double min, double max, double result, String unit) {
		this.name = name;
		this.dataType = dataType;
		this.factor = factor;
		this.min = min;
		this.max = max;
		this.unit = unit;
		this.result = result;
		
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public int getFactor() {
		return factor;
	}

	public void setFactor(int factor) {
		this.factor = factor;
	}

	public double getResult() {
		return result;
	}

	public void setResult(double result) {
		this.result = result;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
	}

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
	}

	public void generaterandom() {

		if (min < 0)
			result = (int) (Math.random() * (max - (min)) + (min));
		else
			result = (int) (Math.random() * max);

	}


}
