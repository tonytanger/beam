/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.beam.sdk.io.gcp.bigtable.changestreams.dao;

import com.google.cloud.bigtable.data.v2.BigtableDataClient;
import com.google.cloud.bigtable.data.v2.models.Range.ByteStringRange;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Data access object to list and read stream partitions of a table. */
@SuppressWarnings({"UnusedVariable", "UnusedMethod"})
public class ChangeStreamDao {
  private static final Logger LOG = LoggerFactory.getLogger(ChangeStreamDao.class);

  private final BigtableDataClient dataClient;
  private final String tableId;

  public ChangeStreamDao(BigtableDataClient dataClient, String tableId) {
    this.dataClient = dataClient;
    this.tableId = tableId;
  }

  /**
   * Returns the result from GenerateInitialChangeStreamPartitions API.
   *
   * @return list of StreamPartition
   */
  public List<ByteStringRange> generateInitialChangeStreamPartitions() {
    return dataClient.generateInitialChangeStreamPartitionsCallable().all().call(tableId);
  }
}
