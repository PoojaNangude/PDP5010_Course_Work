package sanctuary;

/**
 *  This documents the possible values that will be used to document the medical
 *  status of a monkey.
 *  <p>
 *  If the monkey has been brought to the sanctuary for the first time then the value is NEW,
 *  If monkey is currently receiving medical attention, or the monkey is moved from an enclosure
 *  to an isolation then the value is TREATMENT,
 *  If the monkey has completed its medical treatment, then the value is READY. When the value of
 *  the medical status of the monkey is READY, only and only then can it be moved to an available
 *  enclosure.
 *  </p>
 *  When the value id READY, only and only then can the monkey be removed from an isolation
 *  and moved to an enclosure.
 */
public enum MonkeyMedicalStatus {
  NEW,
  TREATMENT,
  READY
}
