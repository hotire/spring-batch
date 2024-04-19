package com.github.hotire.springbatch.core.transaction;

import org.springframework.batch.support.transaction.ResourcelessTransactionManager;

/**
 * @see ResourcelessTransactionManager
 */
public class ResourcelessTransactionManagerCore {

    private static class ResourcelessTransaction {

        private boolean active = false;

        public boolean isActive() {
            return active;
        }

        public void begin() {
            active = true;
        }

        public void clear() {
            active = false;
        }

    }

}
