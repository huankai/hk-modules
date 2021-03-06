package com.hk.core.query;

public enum MatchMode {

	EXACT {
		public String toMatchString(String pattern) {
			return pattern;
		}
	},
	START {
		public String toMatchString(String pattern) {
			return String.format("%s%%", pattern);
		}
	},
	END {
		public String toMatchString(String pattern) {
			return String.format("%%%s", pattern);
		}
	},
	ANYWHERE {
		public String toMatchString(String pattern) {
			return String.format("%%%s%%", pattern);
		}
	};

	private MatchMode() {

	}

	public abstract String toMatchString(String pattern);

}
