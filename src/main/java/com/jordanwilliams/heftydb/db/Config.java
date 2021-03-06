/*
 * Copyright (c) 2014. Jordan Williams
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jordanwilliams.heftydb.db;

import com.jordanwilliams.heftydb.compact.CompactionStrategies;
import com.jordanwilliams.heftydb.compact.CompactionStrategy;

import java.nio.file.Path;

/**
 * Encapsulates all of the tunable values for a database instance.
 */
public class Config {

    public static class Builder {

        //Default config values
        private CompactionStrategy compactionStrategy = CompactionStrategies.SIZE_TIERED_COMPACTION_STRATEGY;
        private int memoryTableSize = 8192000;
        private int tableBlockSize = 16384;
        private int indexBlockSize = 65536;
        private int tableWriterThreads = 4;
        private int tableCompactionThreads = 8;
        private long tableCacheSize = 128000000;
        private long indexCacheSize = 32000000;
        private long maxCompactionRate = 32768000;
        private long maxWriteRate = 32768000;
        private boolean printMetrics = false;
        private Path tableDirectory;
        private Path logDirectory;

        public Builder memoryTableSize(int memoryTableSize) {
            this.memoryTableSize = memoryTableSize;
            return this;
        }

        public Builder tableBlockSize(int tableBlockSize) {
            this.tableBlockSize = tableBlockSize;
            return this;
        }

        public Builder indexBlockSize(int indexBlockSize) {
            this.indexBlockSize = indexBlockSize;
            return this;
        }

        public Builder compactionStrategy(CompactionStrategy compactionStrategy) {
            this.compactionStrategy = compactionStrategy;
            return this;
        }

        public Builder tableWriterThreads(int tableWriterThreads) {
            this.tableWriterThreads = tableWriterThreads;
            return this;
        }

        public Builder tableCompactionThreads(int tableCompactionThreads) {
            this.tableCompactionThreads = tableCompactionThreads;
            return this;
        }

        public Builder tableCacheSize(long tableCacheSize) {
            this.tableCacheSize = tableCacheSize;
            return this;
        }

        public Builder indexCacheSize(long indexCacheSize) {
            this.indexCacheSize = indexCacheSize;
            return this;
        }

        public Builder printMetrics(boolean printMetrics) {
            this.printMetrics = printMetrics;
            return this;
        }

        public Builder directory(Path directory) {
            this.tableDirectory = directory;
            this.logDirectory = directory;
            return this;
        }

        public Builder tableDirectory(Path tableDirectory) {
            this.tableDirectory = tableDirectory;
            return this;
        }

        public Builder logDirectory(Path logDirectory) {
            this.logDirectory = logDirectory;
            return this;
        }

        public Builder maxCompactionRate(long maxCompactionRate) {
            this.maxCompactionRate = maxCompactionRate;
            return this;
        }

        public Builder maxWriteRate(long maxWriteRate) {
            this.maxWriteRate = maxWriteRate;
            return this;
        }

        public Config build() {
            return new Config(compactionStrategy, memoryTableSize, tableBlockSize, indexBlockSize,
                    tableWriterThreads, tableCompactionThreads, tableCacheSize, indexCacheSize, printMetrics,
                    tableDirectory, logDirectory, maxCompactionRate, maxWriteRate);
        }
    }

    private final CompactionStrategy compactionStrategy;
    private final int memoryTableSize;
    private final int tableBlockSize;
    private final int indexBlockSize;
    private final int tableWriterThreads;
    private final int tableCompactionThreads;
    private final long tableCacheSize;
    private final long indexCacheSize;
    private final boolean printMetrics;
    private final Path tableDirectory;
    private final Path logDirectory;
    private final long maxCompactionRate;
    private final long maxWriteRate;

    public Config(CompactionStrategy compactionStrategy, int memoryTableSize, int tableBlockSize, int indexBlockSize,
                  int tableWriterThreads, int tableCompactionThreads, long tableCacheSize, long indexCacheSize,
                  boolean printMetrics, Path tableDirectory, Path logDirectory, long maxCompactionRate,
                  long maxWriteRate) {
        this.compactionStrategy = compactionStrategy;
        this.memoryTableSize = memoryTableSize;
        this.tableBlockSize = tableBlockSize;
        this.indexBlockSize = indexBlockSize;
        this.tableWriterThreads = tableWriterThreads;
        this.tableCompactionThreads = tableCompactionThreads;
        this.tableCacheSize = tableCacheSize;
        this.indexCacheSize = indexCacheSize;
        this.printMetrics = printMetrics;
        this.tableDirectory = tableDirectory;
        this.logDirectory = logDirectory;
        this.maxCompactionRate = maxCompactionRate;
        this.maxWriteRate = maxWriteRate;
    }

    public CompactionStrategy compactionStrategy() {
        return compactionStrategy;
    }

    public int memoryTableSize() {
        return memoryTableSize;
    }

    public int tableBlockSize() {
        return tableBlockSize;
    }

    public int indexBlockSize() {
        return indexBlockSize;
    }

    public int tableWriterThreads() {
        return tableWriterThreads;
    }

    public int tableCompactionThreads() {
        return tableCompactionThreads;
    }

    public long tableCacheSize() {
        return tableCacheSize;
    }

    public long indexCacheSize() {
        return indexCacheSize;
    }

    public boolean autoPrintMetrics() {
        return printMetrics;
    }

    public Path tableDirectory() {
        return tableDirectory;
    }

    public Path logDirectory() {
        return logDirectory;
    }

    public long maxCompactionRate() {
        return maxCompactionRate;
    }

    public long maxWriteRate() {
        return maxWriteRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Config config = (Config) o;

        if (indexBlockSize != config.indexBlockSize) return false;
        if (indexCacheSize != config.indexCacheSize) return false;
        if (maxCompactionRate != config.maxCompactionRate) return false;
        if (maxWriteRate != config.maxWriteRate) return false;
        if (memoryTableSize != config.memoryTableSize) return false;
        if (printMetrics != config.printMetrics) return false;
        if (tableBlockSize != config.tableBlockSize) return false;
        if (tableCacheSize != config.tableCacheSize) return false;
        if (tableCompactionThreads != config.tableCompactionThreads) return false;
        if (tableWriterThreads != config.tableWriterThreads) return false;
        if (compactionStrategy != null ? !compactionStrategy.equals(config.compactionStrategy) : config
                .compactionStrategy != null)
            return false;
        if (logDirectory != null ? !logDirectory.equals(config.logDirectory) : config.logDirectory != null)
            return false;
        if (tableDirectory != null ? !tableDirectory.equals(config.tableDirectory) : config.tableDirectory != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = compactionStrategy != null ? compactionStrategy.hashCode() : 0;
        result = 31 * result + memoryTableSize;
        result = 31 * result + tableBlockSize;
        result = 31 * result + indexBlockSize;
        result = 31 * result + tableWriterThreads;
        result = 31 * result + tableCompactionThreads;
        result = 31 * result + (int) (tableCacheSize ^ (tableCacheSize >>> 32));
        result = 31 * result + (int) (indexCacheSize ^ (indexCacheSize >>> 32));
        result = 31 * result + (printMetrics ? 1 : 0);
        result = 31 * result + (tableDirectory != null ? tableDirectory.hashCode() : 0);
        result = 31 * result + (logDirectory != null ? logDirectory.hashCode() : 0);
        result = 31 * result + (int) (maxCompactionRate ^ (maxCompactionRate >>> 32));
        result = 31 * result + (int) (maxWriteRate ^ (maxWriteRate >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Config{" +
                "compactionStrategy=" + compactionStrategy +
                ", memoryTableSize=" + memoryTableSize +
                ", tableBlockSize=" + tableBlockSize +
                ", indexBlockSize=" + indexBlockSize +
                ", tableWriterThreads=" + tableWriterThreads +
                ", tableCompactionThreads=" + tableCompactionThreads +
                ", tableCacheSize=" + tableCacheSize +
                ", indexCacheSize=" + indexCacheSize +
                ", autoPrintMetrics=" + printMetrics +
                ", tableDirectory=" + tableDirectory +
                ", logDirectory=" + logDirectory +
                ", maxCompactionRate=" + maxCompactionRate +
                ", maxWriteRate=" + maxWriteRate +
                '}';
    }
}
