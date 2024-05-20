package site.rainbowx

import scala.io.{Source, StdIn}

/**
 * @author RainbowX
 * createTime: 2024/5/20 11:24
 * description: Scala 主函数
*/

class Student(val id: Int, val gender: String, val courses: List[Int]) {

}

object Main {
  def main(args: Array[String]): Unit = {
    case class Student(id: String, gender: String, math: Int, english: Int, physics: Int)

    // 解析输入数据
    val data = """
                 |301610 male 80 64 78
                 |301611 female 65 87 58
                 |301612 female 44 71 77
                 |301613 female 66 71 91
                 |301614 female 70 71 100
                 |301615 male 72 77 72
                 |301616 female 73 81 75
                 |301617 female 69 77 75
                 |301618 male 73 61 65
                 |301619 male 74 69 68
                 |301620 male 76 62 76
                 |301621 male 73 69 91
                 |301622 male 55 69 61
                 |301623 male 50 58 75
                 |301624 female 63 83 93
                 |301625 male 72 54 100
                 |301626 male 76 66 73
                 |301627 male 82 87 79
                 |301628 female 62 80 54
                 |301629 male 89 77 72
    """.stripMargin.trim

    val students = data.split("\n").toList.map { line =>
      val Array(id, gender, math, english, physics) = line.split(" ")
      Student(id, gender, math.toInt, english.toInt, physics.toInt)
    }

    // 按性别分组
    val groupedByGender = students.groupBy(_.gender)

    // 计算统计信息
    def calculateStatistics(students: List[Student], subject: Student => Int): (Double, Int, Int) = {
      val scores = students.map(subject)
      val avg = scores.sum.toDouble / scores.length
      val min = scores.min
      val max = scores.max
      (avg, min, max)
    }

    // 输出统计信息
    def printStatistics(gender: String, students: List[Student]): Unit = {
      val subjects = List(
        ("Math", (s: Student) => s.math),
        ("English", (s: Student) => s.english),
        ("Physics", (s: Student) => s.physics)
      )
      println(s"Statistics for $gender:")
      subjects.foreach { case (subjectName, subjectFunc) =>
        val (avg, min, max) = calculateStatistics(students, subjectFunc)
        println(f"$subjectName: avg = $avg%.2f, min = $min, max = $max")
      }
    }

    // 按性别输出统计信息
    groupedByGender.foreach { case (gender, students) =>
      printStatistics(gender, students)
    }
  }
}