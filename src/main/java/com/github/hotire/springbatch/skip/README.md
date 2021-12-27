# Skip

### Getting Started

- https://www.baeldung.com/spring-batch-skip-logic


## faultTolerant

- FaultTolerantStepBuilder extends SimpleStepBuilder

### SkipPolicy

~~~java
	protected SkipPolicy createSkipPolicy() {
		SkipPolicy skipPolicy = this.skipPolicy;
		Map<Class<? extends Throwable>, Boolean> map = new HashMap<>(
				skippableExceptionClasses);
		map.put(ForceRollbackForWriteSkipException.class, true);
		LimitCheckingItemSkipPolicy limitCheckingItemSkipPolicy = new LimitCheckingItemSkipPolicy(skipLimit, map);
		if (skipPolicy == null) {
			Assert.state(!(skippableExceptionClasses.isEmpty() && skipLimit > 0),
					"If a skip limit is provided then skippable exceptions must also be specified");
			skipPolicy = limitCheckingItemSkipPolicy;
		}
		else if (limitCheckingItemSkipPolicy != null) {
			skipPolicy = new CompositeSkipPolicy(new SkipPolicy[] { skipPolicy, limitCheckingItemSkipPolicy });
		}
		return skipPolicy;
	}
~~~

skipPolicy, limitCheckingItemSkipPolicy 순서로 들어간다. 

### CompositeSkipPolicy

~~~java
    @Override
	public boolean shouldSkip(Throwable t, int skipCount) throws SkipLimitExceededException {
		for (SkipPolicy policy : skipPolicies) {
			if (policy.shouldSkip(t, skipCount)) {
				return true;
			}
		}
		return false;
	}
~~~

