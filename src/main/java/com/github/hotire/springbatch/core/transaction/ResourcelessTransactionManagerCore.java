package com.github.hotire.springbatch.core.transaction;

import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.transaction.TransactionException;

/**
 * @see ResourcelessTransactionManager
 */
public class ResourcelessTransactionManagerCore {

    /**
     * @see ResourcelessTransactionManager#doGetTransaction()
     */
    protected Object doGetTransaction() throws TransactionException {
        Object transaction = new ResourcelessTransaction();
        return transaction;
    }

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
