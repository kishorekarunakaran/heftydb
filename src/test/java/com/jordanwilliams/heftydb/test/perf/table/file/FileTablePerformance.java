/*
 * Copyright (c) 2013. Jordan Williams
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

package com.jordanwilliams.heftydb.test.perf.table.file;

import com.jordanwilliams.heftydb.metrics.StopWatch;
import com.jordanwilliams.heftydb.record.Key;
import com.jordanwilliams.heftydb.record.Record;
import com.jordanwilliams.heftydb.record.Value;
import com.jordanwilliams.heftydb.state.Paths;
import com.jordanwilliams.heftydb.table.file.FileTable;
import com.jordanwilliams.heftydb.table.file.IndexBlock;
import com.jordanwilliams.heftydb.table.file.RecordBlock;
import com.jordanwilliams.heftydb.test.generator.ConfigGenerator;
import com.jordanwilliams.heftydb.test.generator.KeyValueGenerator;
import com.jordanwilliams.heftydb.test.util.TestFileUtils;
import com.jordanwilliams.heftydb.util.ByteBuffers;
import com.jordanwilliams.heftydb.write.FileTableWriter;

import java.util.Random;

public class FileTablePerformance {

    private static final int RECORD_COUNT = 25000000;

    public static void main(String[] args) throws Exception {
        TestFileUtils.createTestDirectory();
        KeyValueGenerator keyValueGenerator = new KeyValueGenerator();
        Value value = new Value(keyValueGenerator.testValue(100));

        Paths paths = ConfigGenerator.testPaths();
        FileTableWriter fileTableWriter = FileTableWriter.open(1, paths, RECORD_COUNT, 32768, 8192, 1);
        for (int i = 0; i < RECORD_COUNT; i++) {
            value.data().rewind();
            fileTableWriter.write(new Record(new Key(ByteBuffers.fromString(i + ""), i), value));
        }

        fileTableWriter.finish();

        FileTable fileTable = FileTable.open(1, paths, new RecordBlock.Cache(32768000), new IndexBlock.Cache(4096000));

        Random random = new Random(System.nanoTime());
        StopWatch watch = StopWatch.start();
        int iterations = 1000000;

        for (int i = 0; i < iterations; i++) {
            fileTable.get(new Key(ByteBuffers.fromString(random.nextInt(RECORD_COUNT) + ""), Long.MAX_VALUE));
        }

        System.out.println(iterations / watch.elapsedSeconds());
        TestFileUtils.cleanUpTestFiles();
    }
}