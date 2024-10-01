package usecase;

import api.GradeDataBase;
import entity.Grade;
import entity.Team;

/**
 * GetAverageGradeUseCase class.
 */
public final class GetAverageGradeUseCase {
    private final GradeDataBase gradeDataBase;

    public GetAverageGradeUseCase(GradeDataBase gradeDataBase) {
        this.gradeDataBase = gradeDataBase;
    }

    /**
     * Get the average grade for a course across your team.
     * @param course The course.
     * @return The average grade.
     */
    public float getAverageGrade(String course) {
        final Team team = gradeDataBase.getMyTeam();

        String[] userNames = gradeDataBase.getMyTeam().getMembers();
        float sum = 0;
        for (String userName : userNames) {
            sum += gradeDataBase.getGrade(userName, course).getGrade();
        }
        int count = userNames.length;

        if (count == 0) {
            return 0;
        }
        return sum / count;
    }
}