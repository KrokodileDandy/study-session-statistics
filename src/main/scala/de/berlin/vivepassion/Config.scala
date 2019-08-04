package de.berlin.vivepassion

import de.berlin.vivepassion.GroupByIntervals.GroupByIntervals

case class Config(
                   debug: Boolean = false,
                   mode: String = "analyse",
                   alone: Boolean = true,
                   groupBy: GroupByIntervals = GroupByIntervals.Day,
                   // study session attributes
                   form: String = "",
                   course: String = "",
                   date: String = "",
                   startTime: String = "",
                   endTime: String = "",
                   pause: Int = 0,
                   comment: String = "",
                   semester: String = VPSConfiguration.properties.getProperty("current_semester")
                 )
