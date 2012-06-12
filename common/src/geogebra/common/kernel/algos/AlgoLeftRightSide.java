package geogebra.common.kernel.algos;

import geogebra.common.kernel.Construction;
import geogebra.common.kernel.Kernel;
import geogebra.common.kernel.StringTemplate;
import geogebra.common.kernel.arithmetic.ExpressionNode;
import geogebra.common.kernel.arithmetic.FunctionNVar;
import geogebra.common.kernel.arithmetic.FunctionVariable;
import geogebra.common.kernel.arithmetic.MyDouble;
import geogebra.common.kernel.geos.GeoConic;
import geogebra.common.kernel.geos.GeoElement;
import geogebra.common.kernel.geos.GeoFunctionNVar;
import geogebra.common.kernel.geos.GeoLine;

public class AlgoLeftRightSide extends AlgoElement {

	private GeoElement equation;
	private GeoFunctionNVar side;
	private boolean left;
	private FunctionVariable[] fv;
	public AlgoLeftRightSide(Construction cons, String label,
			GeoElement equation, boolean left) {
		super(cons);
		this.equation = equation;
		this.left = left;
		
		fv = new FunctionVariable[]{new FunctionVariable(kernel,"x"),
				new FunctionVariable(kernel,"y")};
		FunctionNVar f = new FunctionNVar(new ExpressionNode(kernel,fv[0]),fv);
		side = new GeoFunctionNVar(cons,f);
		
		setInputOutput();
		compute();
		side.setLabel(label);
	}

	@Override
	protected void setInputOutput() {
		setOnlyOutput(side);
		input = new GeoElement[]{equation};
		setDependencies();
	}

	@Override
	public void compute() {
		if(!equation.isDefined()){
			side.setUndefined();
			return;
		}
		ExpressionNode expr;
		String str = equation.toValueString(StringTemplate.maxPrecision);
		String[] sides = str.split("=");
		String sideStr = left ? sides[0]:sides[1];
		GeoFunctionNVar processed = kernel.getAlgebraProcessor().evaluateToFunctionNVar(sideStr, true);
		side.set(processed);

	}


	@Override
	public Algos getClassName() {
		return left ? Algos.AlgoLeftSide : Algos.AlgoRightSide;
	}

	public GeoFunctionNVar getResult() {
		return side;
	}

}
