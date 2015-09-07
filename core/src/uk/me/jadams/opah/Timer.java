package uk.me.jadams.opah;

public class Timer
{
    private float best;

    private float timer;

    public Timer()
    {
        best = 0;
        timer = 0;
    }

    public void start()
    {
        timer = 0;
    }

    public void update(float delta)
    {
        timer += delta;
    }

    public void stop()
    {
        if (timer > best)
        {
            best = timer;
            System.out.println("New Record: " + timer);
        }
        else
        {
            System.out.println("Time: " + timer + " (previous best: " + best);
        }
    }
}
