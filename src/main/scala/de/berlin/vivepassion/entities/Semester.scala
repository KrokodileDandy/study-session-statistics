package de.berlin.vivepassion.entities

import java.sql.ResultSet
import java.time.{Instant, LocalDate, ZoneId}

/**
 * Entity which represents a university semester with a variable lengths.
 *
 * @param id Id of the semesterName in the database.
 * @param name Name of the semesterN.
 * @param start Date of the first day of the semesterName.
 * @param end Date of the last day of the semesterName.
 *
 * @author Sebastian Führ
 * @version 0.1
 */
final case class Semester(id: Long, name: String, start: LocalDate, end: LocalDate) extends Entity[Semester] {

  /**
   * @inheritdoc
   * @return Saved instance of T.
   */
  @Override
  override def getEntityClass: Semester = this

} // ----- End of 'case class Semester'
object Semester extends EntityObjectInterface[Semester] {

  /**
   * Secondary constructor
   * @param name Name of the semester instance.
   */
  final def apply(name: String) = new Semester(-1, name, null, null)

  /**
   * Converts a java ResultSet into a scala List[Semester].
   * @param resultSet ResultSet to convert
   * @return List of semesters
   */
  @Override
  final override def resultSetToList(resultSet: ResultSet): List[Semester] = {
    new Iterator[Semester] { // https://stackoverflow.com/questions/9636545/treating-an-sql-resultset-like-a-scala-stream
      def hasNext: Boolean = resultSet.next()
      def next(): Semester = { // here a typecast happens
        val name = resultSet.getString("semester_name")
        val startDate = Instant.ofEpochMilli(resultSet.getInt("start_date").toLong)
          .atZone(ZoneId.systemDefault()).toLocalDate
        val endDate = Instant.ofEpochMilli(resultSet.getInt("end_date").toLong)
          .atZone(ZoneId.systemDefault()).toLocalDate
        val id = resultSet.getLong("id")
        Semester(id, name, startDate, endDate)
      }
    }.toList
  } // ----- End of resultSetToList

} // ----- End of 'object Semester'
