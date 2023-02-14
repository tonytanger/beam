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
package org.apache.beam.sdk.io.gcp.bigtable.changestreams.model;

import static org.apache.beam.sdk.io.gcp.bigtable.changestreams.ByteStringRangeHelper.formatByteStringRange;

import com.google.cloud.Timestamp;
import com.google.cloud.bigtable.data.v2.models.ChangeStreamContinuationToken;
import com.google.cloud.bigtable.data.v2.models.Range.ByteStringRange;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nullable;
import org.apache.beam.sdk.annotations.Internal;

/**
 * Output result of {@link
 * org.apache.beam.sdk.io.gcp.bigtable.changestreams.dofn.DetectNewPartitionsDoFn} containing
 * information required to stream a partition.
 */
@Internal
public class PartitionRecord implements Serializable {
  private static final long serialVersionUID = -7613861834142734474L;

  private ByteStringRange partition;
  @Nullable private Timestamp startTime;
  @Nullable private List<ChangeStreamContinuationToken> changeStreamContinuationTokens;
  private String uuid;
  private Timestamp parentLowWatermark;

  public PartitionRecord(
      ByteStringRange partition, Timestamp startTime, String uuid, Timestamp parentLowWatermark) {
    this.partition = partition;
    this.startTime = startTime;
    this.uuid = uuid;
    this.parentLowWatermark = parentLowWatermark;
  }

  public PartitionRecord(
      ByteStringRange partition,
      List<ChangeStreamContinuationToken> changeStreamContinuationTokens,
      String uuid,
      Timestamp parentLowWatermark) {
    this.partition = partition;
    this.changeStreamContinuationTokens = changeStreamContinuationTokens;
    this.uuid = uuid;
    this.parentLowWatermark = parentLowWatermark;
  }

  @Nullable
  public Timestamp getStartTime() {
    return startTime;
  }

  public void setStartTime(@Nullable Timestamp startTime) {
    this.startTime = startTime;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public Timestamp getParentLowWatermark() {
    return parentLowWatermark;
  }

  public void setParentLowWatermark(Timestamp parentLowWatermark) {
    this.parentLowWatermark = parentLowWatermark;
  }

  public ByteStringRange getPartition() {
    return partition;
  }

  public void setPartition(ByteStringRange partition) {
    this.partition = partition;
  }

  @Nullable
  public List<ChangeStreamContinuationToken> getChangeStreamContinuationTokens() {
    return changeStreamContinuationTokens;
  }

  public void setChangeStreamContinuationTokens(
      @Nullable List<ChangeStreamContinuationToken> changeStreamContinuationTokens) {
    this.changeStreamContinuationTokens = changeStreamContinuationTokens;
  }

  @Override
  public boolean equals(@Nullable Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PartitionRecord)) {
      return false;
    }
    PartitionRecord that = (PartitionRecord) o;
    return getPartition().equals(that.getPartition())
        && Objects.equals(getStartTime(), that.getStartTime())
        && Objects.equals(
            getChangeStreamContinuationTokens(), that.getChangeStreamContinuationTokens())
        && getUuid().equals(that.getUuid())
        && Objects.equals(getParentLowWatermark(), that.getParentLowWatermark());
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        getPartition(),
        getStartTime(),
        getChangeStreamContinuationTokens(),
        getUuid(),
        getParentLowWatermark());
  }

  @Override
  public String toString() {
    return "PartitionRecord{"
        + "partition="
        + formatByteStringRange(partition)
        + ", startTime="
        + startTime
        + ", changeStreamContinuationTokens="
        + changeStreamContinuationTokens
        + ", uuid='"
        + uuid
        + '\''
        + ", parentLowWatermark="
        + parentLowWatermark
        + '}';
  }
}
