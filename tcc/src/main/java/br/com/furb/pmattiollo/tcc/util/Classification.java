package br.com.furb.pmattiollo.tcc.util;

import java.math.BigDecimal;
import java.util.List;

import br.com.furb.pmattiollo.tcc.domain.CollectEntity;
import br.com.furb.pmattiollo.tcc.domain.ItemEntity;

public class Classification {
	
	public ItemEntity item;
	public List<CollectEntity> collects;
	public Calculation calcXI;
	public Calculation calcMMEP;
	public Calculation calcDEF;	
	
	public Classification(ItemEntity item, List<CollectEntity> collects, Calculation calcXI, Calculation calcMMEP, Calculation calcDefs) {
		this.item = item;
		this.collects = collects;
		this.calcXI = calcXI;
		this.calcMMEP = calcMMEP;
		this.calcDEF = calcDefs;
	}

	public boolean isStable() {
		return stableTest1() && stableTest2() && stableTest3() && stableTest4();
	}
	
	public boolean isAble(boolean isStable) {
		return isStable && ableTestByCalc(calcXI) && ableTestByCalc(calcMMEP) && ableTestByCalc(calcDEF);
	}
	
	private boolean stableTest1() {
		for(CollectEntity collect : collects) {
			if(collect.getValue().compareTo(calcXI.getUclResult()) > 0 || collect.getValue().compareTo(calcMMEP.getUclResult()) > 0 || collect.getValue().compareTo(calcDEF.getUclResult()) > 0) {
				return false;
			}
			
			if(collect.getValue().compareTo(calcXI.getLclResult()) < 0 || collect.getValue().compareTo(calcMMEP.getLclResult()) < 0 || collect.getValue().compareTo(calcDEF.getLclResult()) < 0) {
				return false;
			}
		}
		
		return true;
	}
	
	private boolean stableTest2() {
		return stableTest2ByCalc(calcXI) && stableTest2ByCalc(calcMMEP) && stableTest2ByCalc(calcDEF);
	}
	
	private boolean stableTest2ByCalc(Calculation calc) {
		BigDecimal valueCalc = calc.getUclResult().subtract(calc.getClResult());
		BigDecimal sigma = valueCalc.divide(new BigDecimal(3), Calculation.SCALE, Calculation.ROUND);
		BigDecimal twoSigma = sigma.multiply(new BigDecimal(2));
		
		BigDecimal controlMaxValue = calc.getClResult().add(twoSigma);
		BigDecimal controlMinValue = calc.getClResult().subtract(twoSigma);
		
		int countSup = 0;
		int countSupTwoSigma = 0;
		int countInf = 0;
		int countInfTwoSigma = 0;
		
		for(CollectEntity collect : collects) {
			if(collect.getValue().compareTo(calc.getClResult()) > 0) {
				countInf = 0;
				countInfTwoSigma = 0;
				
				countSup ++;
				
				if(collect.getValue().compareTo(controlMaxValue) > 0) {
					countSupTwoSigma ++;
				}
			} else if(collect.getValue().compareTo(calc.getClResult()) < 0) {
				countSup = 0;
				countSupTwoSigma = 0;
				
				countInf ++;
				
				if(collect.getValue().compareTo(controlMinValue) < 0) {
					countInfTwoSigma ++;
				}
			}
			
			
			if((countSup >= 3 && countSupTwoSigma == 2) || (countInf >= 3 && countInfTwoSigma == 2)) {
				return false;
			}
		}
		
		return true;
	}
	
	private boolean stableTest3() {
		return stableTest3ByCalc(calcXI) && stableTest3ByCalc(calcMMEP) && stableTest3ByCalc(calcDEF);
	}
	
	private boolean stableTest3ByCalc(Calculation calc) {
		BigDecimal valueCalc = calc.getUclResult().subtract(calc.getClResult());
		BigDecimal sigma = valueCalc.divide(new BigDecimal(3), Calculation.SCALE, Calculation.ROUND);
		
		BigDecimal controlMaxValue = calc.getClResult().add(sigma);
		BigDecimal controlMinValue = calc.getClResult().subtract(sigma);
		
		int countSup = 0;
		int countSupSigma = 0;
		int countInf = 0;
		int countInfSigma = 0;
		
		for(CollectEntity collect : collects) {
			if(collect.getValue().compareTo(calc.getClResult()) > 0) {
				countInf = 0;
				countInfSigma = 0;
				
				countSup ++;
				
				if(collect.getValue().compareTo(controlMaxValue) > 0) {
					countSupSigma ++;
				}
			} else if(collect.getValue().compareTo(calc.getClResult()) < 0) {
				countSup = 0;
				countSupSigma = 0;
				
				countInf ++;
				
				if(collect.getValue().compareTo(controlMinValue) < 0) {
					countInfSigma ++;
				}
			}
			
			
			if((countSup >= 5 && countSupSigma == 4) || (countInf >= 5 && countInfSigma == 4)) {
				return false;
			}
		}
		
		return true;
	}
	
	private boolean stableTest4() {
		return stableTest4ByCalc(calcXI) && stableTest4ByCalc(calcMMEP) && stableTest4ByCalc(calcDEF);
	}
	
	private boolean stableTest4ByCalc(Calculation calc) {		
		int countSup = 0;
		int countInf = 0;
		
		for(CollectEntity collect : collects) {
			if(collect.getValue().compareTo(calc.getClResult()) > 0) {
				countInf = 0;
				
				countSup ++;
			} else if(collect.getValue().compareTo(calc.getClResult()) < 0) {
				countSup = 0;
				
				countInf ++;
			}			
			
			if(countSup >= 8 || countInf >= 8) {
				return false;
			}
		}
		
		return true;
	}
	
	private boolean ableTestByCalc(Calculation calc) {
		BigDecimal usl = item.getUsl();
		BigDecimal lsl = item.getLsl();
		BigDecimal ucl = calc.getUclResult();
		BigDecimal lcl = calc.getLclResult().max(new BigDecimal(0));
		
		BigDecimal capacityUser = usl.subtract(lsl);
		BigDecimal capacityProcess = ucl.subtract(lcl);
		
		return capacityUser.divide(capacityProcess, Calculation.SCALE, Calculation.ROUND).compareTo(new BigDecimal(1)) > 0;
	}

}
