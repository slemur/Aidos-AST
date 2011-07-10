package org.aidos.tree.analyzer.flow;



public class FlowBlock {
	
	private int flowStartOffset;
	private FlowAnalyzer analyzer;
	private boolean analyzed;
	
	public FlowBlock(FlowAnalyzer analyzer, int flowStartOffset) {
		this.analyzer = analyzer;
		this.flowStartOffset = flowStartOffset;
	}

	public int getFlowStartOffset() {
		return flowStartOffset;
	}

	public FlowAnalyzer getAnalyzer() {
		return analyzer;
	}
	
	public boolean isAnalyzed() {
		return analyzed;
	}
	
	public void setAnalyzed(boolean analyzed) {
		this.analyzed = analyzed;
	}
	
}
