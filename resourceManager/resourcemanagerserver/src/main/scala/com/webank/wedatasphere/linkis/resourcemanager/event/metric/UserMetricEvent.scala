/*
 * Copyright 2019 WeBank
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.webank.wedatasphere.linkis.resourcemanager.event.metric

import com.webank.wedatasphere.linkis.resourcemanager.domain.{UserReleasedResource, UserUsedResource}
import com.webank.wedatasphere.linkis.scheduler.queue.SchedulerEventState.SchedulerEventState

/**
  * Created by shanhuang on 9/11/18.
  */
abstract class UserMetricEvent extends MetricRMEvent {
  val user: String

  override def resume(): Unit = {}

  override def afterStateChanged(fromState: SchedulerEventState, toState: SchedulerEventState): Unit = {}
}

class UserSessionStartEvent(override val user: String, val creator: String, val userUsedResource: UserUsedResource) extends UserMetricEvent {
  override val moduleName: String = userUsedResource.moduleInstance.getApplicationName
}

class UserSessionEndEvent(override val user: String, val userReleasedResource: UserReleasedResource) extends UserMetricEvent {
  override val moduleName: String = userReleasedResource.moduleInstance.getApplicationName
}
