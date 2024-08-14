package com.svetanis.algorithms.sorting.mergesort.interval;

public final class EmployeeInterval {
  protected Interval interval;	 
  protected int empIndex; 
  protected int intIndex;

  public EmployeeInterval(Interval interval, int empIndex, int intIndex) {
    this.interval = interval;
	this.empIndex = empIndex;
    this.intIndex = intIndex;
  }
}