package org.aidos.tree.analyzer.flow;

/**
 * Represents a flow block.
 * @author `Discardedx2
 */
public class FlowBlock {
	
	/**
	 * The code offset of this block.
	 */
	private int flowStartOffset;
	/**
	 * The analyzer that analyzed this block.
	 */
	private FlowAnalyzer analyzer;
	/**
	 * @return {@code true} if this block has been analyzed before, otherwise {@code false}.
	 */
	private boolean analyzed;
	
	/**
	 * Constructs a new flow block.
	 * @param analyzer The analyzer used to analyze this block.
	 * @param flowStartOffset The start offset of this block.
	 */
	public FlowBlock(FlowAnalyzer analyzer, int flowStartOffset) {
		this.analyzer = analyzer;
		this.flowStartOffset = flowStartOffset;
	}
	
	
	/**
	 * Gets this block's start offset.
	 * @return The start offset.
	 */
	public int getFlowStartOffset() {
		return flowStartOffset;
	}

	/**
	 * Gets this block's analyzer.
	 * @return The analyzer.
	 */
	public FlowAnalyzer getAnalyzer() {
		return analyzer;
	}
	
	/**
	 * Checks if this {@link FlowBlock} has been analyzed yet.
	 * @return {@code true} if this block has been analyzed before, otherwise {@code false}.
	 */
	public boolean isAnalyzed() {
		return analyzed;
	}
	
	/**
	 * Sets this block analyzed.
	 * @param analyzed The analyzed value to set.
	 */
	public void setAnalyzed(boolean analyzed) {
		this.analyzed = analyzed;
	}
	
}
