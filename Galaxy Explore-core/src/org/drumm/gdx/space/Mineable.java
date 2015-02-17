package org.drumm.gdx.space;

import java.util.Collection;

public interface Mineable {
	
	public enum Resource {CREDITS};
	
	public Collection<Resource> getMinableTypes();
	
	public boolean isMinableFor(Resource type);
	
	public float getTotalMinable(Resource type);
	
	public MineResult mine(Resource type);
	
	public static class MineResult{
		private Resource resource;
		private float amount;
		public  MineResult(Resource resource, float amount){
			this.resource=resource;
			this.amount=amount;
		}
		public Resource getResource() {
			return resource;
		}
		public float getAmount() {
			return amount;
		}
	}

}
